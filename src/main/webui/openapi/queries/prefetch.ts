// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { type QueryClient } from "@tanstack/react-query";
import { getHaCoreInfo, getHaDumpEnv } from "../requests/services.gen";
import * as Common from "./common";
export const prefetchUseGetHaCoreInfo = (queryClient: QueryClient, clientOptions: Options<unknown, true> = {}) => queryClient.prefetchQuery({ queryKey: Common.UseGetHaCoreInfoKeyFn(clientOptions), queryFn: () => getHaCoreInfo({ ...clientOptions }).then(response => response.data) });
export const prefetchUseGetHaDumpEnv = (queryClient: QueryClient, clientOptions: Options<unknown, true> = {}) => queryClient.prefetchQuery({ queryKey: Common.UseGetHaDumpEnvKeyFn(clientOptions), queryFn: () => getHaDumpEnv({ ...clientOptions }).then(response => response.data) });
